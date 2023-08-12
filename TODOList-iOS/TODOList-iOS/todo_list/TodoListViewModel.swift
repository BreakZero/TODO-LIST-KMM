//
//  TodoListViewModel.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import data

extension TodoListScreen {
    @MainActor class TodoListViewModel: ObservableObject {
        
        
        @Published private(set) var tasks: [ModelTask] = []
        @Published private(set) var showAddTaskSheet: Bool = false
        
        func onShowAddTaskSheetChanged(isShow: Bool) {
            print("show value: \(isShow)")
            self.showAddTaskSheet = isShow
        }
        
        init() {
//            let fileManager = FileManager.default
//            let documentsURL = fileManager.urls(for: .documentDirectory, in: .userDomainMask)[0]
//            do {
//                let fileURLs = try fileManager.contentsOfDirectory(at: documentsURL, includingPropertiesForKeys: nil)
//                print(fileURLs)
//                // process files
//            } catch {
//                print("Error while enumerating files \(documentsURL.path): \(error.localizedDescription)")
//            }
            KoinManager.helper.loadTasks(
                onEach: { tasks in
                    self.tasks = tasks
                },
                onComplete: {},
                onError: {error in}
            )
        }
    }
}
