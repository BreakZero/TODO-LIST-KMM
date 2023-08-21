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
            self.showAddTaskSheet = isShow
        }
        
        init() {
            KoinManager.helper.loadTasks(
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
            KoinManager.helper.insertTask(
                task: task,
                completionHandler: { error in
                    if error == nil {
                        onCompletion()
                    } else {
//                        self.error = Error.InsertSomethingWrong
                    }
                }
            )
        }
    }
}
