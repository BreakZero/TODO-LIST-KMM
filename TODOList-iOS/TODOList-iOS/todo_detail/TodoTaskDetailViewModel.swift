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
        
        func fetch(taskId: Int64) {
            KoinManager.helper.findTaskById(
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
            print("editted task: \(task.title), desc: \(task.description_)")
            KoinManager.helper.updateTask(
                task: task,
                completionHandler: { error in
                    if error == nil {
                        onCompeletion()
                    } else {
                        //
                    }
                }
            )
        }
    }
}
