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
        
        private var insertTask: Task<Void, Never>? = nil
        
        func onShowAddTaskSheetChanged(isShow: Bool) {
            self.showAddTaskSheet = isShow
        }
        
        init() {
            KoinManager.taskRepository.loadTasks(
                onEach: { tasks in
                    self.tasks = tasks
                },
                onComplete: {},
                onError: {error in}
            )
        }
        
        func insertTask(
            task: ModelTask,
            onCompletion: @escaping () -> Void
        ) {
            insertTask = Task {
                do {
                    _ = try await suspend(KoinManager.taskRepository.insertTask(task: task))
                    onCompletion()
                } catch {
                    debugPrint("we get an error \(error)")
                }
            }
        }
        
        func onCleaned() {
            insertTask?.cancel()
            insertTask = nil
        }
    }
}
