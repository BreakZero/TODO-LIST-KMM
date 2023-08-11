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
//            self.tasks = [
//                ModelTask(id: 1, title: "Title1", description: "Description1", accentColor: 0xFFF79E89, deadline: currentTimeInMilliSeconds() + 36000, attachment: nil, createAt: currentTimeInMilliSeconds()),
//                ModelTask(id: 2, title: "Title2", description: "Description2", accentColor: 0xFFF79E89, deadline: currentTimeInMilliSeconds() + 36000, attachment: nil, createAt: currentTimeInMilliSeconds()),
//                ModelTask(id: 3, title: "Title3", description: "Description3", accentColor: 0xFFF79E89, deadline: currentTimeInMilliSeconds() + 36000, attachment: nil, createAt: currentTimeInMilliSeconds())
//            ]
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
