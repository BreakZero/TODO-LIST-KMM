//
//  TaskFormContract.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/17.
//  Copyright © 2023 orgName. All rights reserved.
//

import data
import _PhotosUI_SwiftUI

extension TaskFormSheet {
    @MainActor final class TaskFormContract: ObservableObject {
        @Published var title: String = ""
        @Published var description: String = ""
        @Published var deadline: Date = Date.now
        @Published var deadlineDescription: String = ""
        
        @Published private(set) var showDatePicker: Bool = false
        
        @Published private(set) var selectedImage: UIImage? = nil
        @Published var imageSelection: PhotosPickerItem? = nil {
            didSet {
                setImage(from: imageSelection)
            }
        }
        private var taskId: Int64? = nil
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
                    debugPrint(error)
                }
            }
        }
        
        @Published private(set) var error: Error? = nil
        
        init() {
            $deadline.map {
                let result = KoinManager.commonHelper.dateFormatted(timestamp: $0.toEpochMillis())
                return result
            }.assign(to: &$deadlineDescription)
        }
        
        func fetch(taskId: Int64) {
            self.taskId = taskId
            KoinManager.taskRepository.findTaskById(
                id: taskId,
                onEach: { task in
                    self.title = task.title
                    self.description = task.description_
                    self.deadline = KoinManager.commonHelper.timestampToDate(timestamp: task.deadline)
                    self.attachment = task.attachment
                    if let bytes = task.attachment {
                        let uiImage = KoinManager.commonHelper.getUIImageFromBytes(bytes: bytes)
                        self.selectedImage = uiImage
                    }
                },
                onComplete: {
                    
                },
                onError: { error in
                    
                }
            )
        }
        
        func onConfirmed(
            onTaskGenerated: @escaping (ModelTask) -> Void
        ) {
            if self.title.isEmpty {
                self.error = Error.TitleEmpty
                return
            }
            if self.description.isEmpty {
                self.error = Error.DescriptionEmpty
                return
            }
            
            let task: ModelTask = ModelTask(
                id: self.taskId ?? -1,
                title: self.title,
                description: self.description,
                accentColor: getCategory(category: TaskCategory.allCases.randomElement() ?? TaskCategory.WORK),
                deadline: self.deadline.toEpochMillis(),
                attachment: attachment,
                createAt: KoinManager.commonHelper.currentMilliseconds()
            )
            onTaskGenerated(task)
        }
        
        func onShowDatePickerChanged(isShow: Bool) {
            self.showDatePicker = isShow
        }
        
        func clearError() {
            self.error = nil
        }
        
        enum Error: LocalizedError {
            case TitleEmpty
            case DescriptionEmpty
            
            var errorDescription: String? {
                switch self {
                case .TitleEmpty: "Title could not empty"
                case .DescriptionEmpty: "Description could not empty"
                }
            }
        }
    }
}
