//
//  TodoTaskDetailScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/12.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TodoTaskDetailScreen: View {
    private var taskId: Int64
    @StateObject var viewModel: ViewModel = ViewModel()
    init(taskId: Int64) {
        self.taskId = taskId
    }
    
    var body: some View {
        VStack {
            if let task = viewModel.task {
                Text(task.title)
                    .font(.title)
                    .bold()
                    .padding(.horizontal, 16)
                    .padding(.top, 4)
                Spacer().frame(height: 8)
                Text(task.description_)
                    .font(.body)
                    .padding(.horizontal, 16)
                if let bytes = task.attachment {
                    let uiImage = KoinManager.helper.getUIImageFromBytes(bytes: bytes)
                    GeometryReader { geo in
                        Image(uiImage: uiImage)
                            .resizable()
                            .scaledToFit()
                            .frame(width: geo.size.width * 0.8)
                            .frame(width: geo.size.width)
                    } 
                }
                Spacer()
                Text("Created at \(task.createAt.asTimeStampToDateFormatted())")
                    .font(.caption)
            }
        }.onAppear {
            viewModel.fetch(taskId: taskId)
        }.toolbar(content: {
            ToolbarItemGroup(placement: .primaryAction) {
                Button(action: {
                    
                }, label: {
                    Image(systemName: "square.and.pencil")
                })
                Button(action: {
                    
                }, label: {
                    Image(systemName: "trash")
                })
            }
        })
    }
}

