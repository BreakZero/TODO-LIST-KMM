//
//  TodoTaskEditViewModel.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import data
import _PhotosUI_SwiftUI

extension TodoTaskEditScreen {
    @MainActor final class ViewModel: ObservableObject {
        @Published var title: String = ""
        @Published var description: String = ""
        @Published var deadline: Date = Date()
        @Published var deadlineDescription: String = ""
        
        @Published private(set) var showDatePicker: Bool = false
        
        @Published private(set) var selectedImage: UIImage? = nil
        @Published var imageSelection: PhotosPickerItem? = nil {
            didSet {
                setImage(from: imageSelection)
            }
        }
        private var attachment: KotlinByteArray? = nil
        
        private func setImage(from selection: PhotosPickerItem?) {
            guard let selection else {return}
            Task {
                do {
                    let data = try await selection.loadTransferable(type: Data.self)
                    guard let data, let uiImage = UIImage(data: data)  else {
                        throw URLError(.badServerResponse)
                    }
                    attachment = KotlinByteArray.from(data: data)
                    selectedImage = uiImage
                } catch {
                    print(error)
                }
            }
        }
        
        @Published private(set) var error: Error? = nil
        
        private let dateFormatter = DateFormatter()
        init() {
            dateFormatter.dateFormat = "HH:mm E, d MMM y"
            $deadline.map {
                print("===== \($0.toMillis() ?? 0)")
                let result = self.dateFormatter.string(from: $0)
                print("====== \(result)")
                return result
            }.assign(to: &$deadlineDescription)
        }
        
        func onShowDatePickerChanged(isShow: Bool) {
            self.showDatePicker = isShow
        }
        
        func insertTask(
            onCompletion: @escaping () -> Void
        ) {
            print("title: \(title), desc: \(description), deadline: \(deadlineDescription)")
            if self.title.isEmpty {
                self.error = Error.TitleEmpty
                return
            }
            if self.description.isEmpty {
                self.error = Error.DescriptionEmpty
                return
            }
            
            let task: ModelTask = ModelTask(
                id: -1,
                title: self.title,
                description: self.description,
                accentColor: getCategory(category: TaskCategory.allCases.randomElement() ?? TaskCategory.WORK),
                deadline: self.deadline.toMillis(),
                attachment: attachment,
                createAt: currentTimeInMilliSeconds()
            )
            KoinManager.helper.insertOrUpdateTask(
                task: task,
                completionHandler: { error in
                    if error == nil {
                        onCompletion()
                    } else {
                        self.error = Error.InsertSomethingWrong
                    }
                }
            )
        }
        
        func clearError() {
            self.error = nil
        }
        
        enum Error: LocalizedError {
            case TitleEmpty
            case DescriptionEmpty
            case InsertSomethingWrong
            
            var errorDescription: String? {
                switch self {
                case .TitleEmpty: "Title could not empty"
                case .DescriptionEmpty: "Description could not empty"
                case .InsertSomethingWrong: "Something went wrong"
                }
            }
        }
    }
}
