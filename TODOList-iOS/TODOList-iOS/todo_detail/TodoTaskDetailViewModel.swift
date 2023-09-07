//
//  TodoTaskDetailViewModel.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import data


extension TodoTaskDetailScreen {
    @MainActor final class ViewModel: ObservableObject {
        @Published private(set) var task: ModelTask? = nil
        
        @Published var isShowEditor: Bool = false
        @Published var isShowDeleteActions: Bool = false
        
        private var tasks: [Task<Void, Never>] = []
        
        func fetch(taskId: Int64) {
            KoinManager.taskRepository.findTaskById(
                id: taskId,
                onEach: { task in
                    self.task = task
                },
                onComplete: {},
                onError: {error in}
            )
        }
        
        func updateTask(
            task: ModelTask,
            onCompeletion: @escaping () -> Void
        ) {
            let task = Task {
                do {
                    print(task.description_)
                    _ = try await suspend(KoinManager.taskRepository.updateTask(task: task))
                    onCompeletion()
                } catch  {
                    print("we get an error: \(error)")
                }
            }
            tasks.append(task)
        }
        
        func deleteTask(
            id: Int64,
            onCompeletion: @escaping () -> Void
        ) {
            let task = Task {
                do {
                    _ = try await suspend(KoinManager.taskRepository.deleteById(id: id))
                    onCompeletion()
                } catch {
                    debugPrint("get an error \(error)")
                }
            }
            tasks.append(task)
        }
        
        func onCleaned() {
            print("===== onclean")
            tasks.forEach({ $0.cancel() })
            tasks = []
        }
    }
}
