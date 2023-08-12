//
//  TodoListScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/8.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct TodoListScreen: View {
    @StateObject private var viewModel = TodoListViewModel()
    var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    AppTopbar(desination: {
                        SettingsScreen()
                    })
                    List(viewModel.tasks, id: \.self.id) { task in
                        ZStack {
                            NavigationLink(destination: Text(task.title), label: {
                                EmptyView()
                            }).opacity(0.0)
                            TaskItemView(task: task)
                        }.listRowInsets(EdgeInsets())
                            .listRowBackground(EmptyView())
                            .listRowSeparator(.hidden)
                    }.listStyle(.inset)
                }
                
                FloatingActionButton(action: {
                    viewModel.onShowAddTaskSheetChanged(isShow: true)
                }, icon: "plus")
            }.sheet(
                isPresented: Binding(
                    get: {viewModel.showAddTaskSheet},
                    set: { value in
                        viewModel.onShowAddTaskSheetChanged(isShow: value)
                    }),
                content: {
                    TodoTaskEditScreen()
                })
        }
    }
}

struct TaskItemView: View {
    var task: ModelTask
    var body: some View {
        VStack {
            HStack {
                Text(task.title)
                    .font(.system(size: 24))
                    .bold()
                    .foregroundColor(Color.white)
                    .lineLimit(1)
                Spacer()
            }
            Text(task.description_).frame(maxWidth: .infinity, alignment: .leading)
                .font(.system(size: 22))
                .padding(.vertical, 2)
                .foregroundColor(Color.white)
                .lineLimit(3)
            Text(task.createAt.asTimeStampToDateFormatted().description)
                .frame(maxWidth: .infinity, alignment: .leading)
                .foregroundColor(Color.white)
            if let data = task.attachment  {
                Image(uiImage: KoinManager.helper.getUIImageFromBytes(bytes: data))
                    .resizable()
                    .scaledToFit()
                    .frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/,height: 100)
                    .cornerRadius(12)
            }
        }
        .padding(.all, 8)
        .background(Color(hex: task.accentColor))
        .cornerRadius(12)
        .padding(.horizontal, 16)
        .padding(.vertical, 4)
    }
}
