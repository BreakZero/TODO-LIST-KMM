//
//  TaskFormEditor.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/17.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import PhotosUI
import UIKit
import data

struct TaskFormSheet: View {
    @StateObject private var formContract = TaskFormContract()
    
    private var taskId: Int64? = nil
    private var confirmButtonText: String
    private var onConfirmed: (ModelTask) -> Void
    
    init(
        taskId: Int64? = nil,
        confirmButtonText: String,
        onConfirmed: @escaping (ModelTask) -> Void
    ) {
        self.confirmButtonText = confirmButtonText
        self.onConfirmed = onConfirmed
        self.taskId = taskId
    }
    
    var body: some View {
        VStack(
            alignment: .center
        ) {
            Image(systemName: "minus")
                .resizable()
                .scaledToFit()
                .frame(height: 4)
                .padding(.vertical, 8)
            TextField("edit_title", text: $formContract.title)
                .textFieldStyle(.todo_default)
            TextField("edit_description", text: $formContract.description, axis: .vertical)
                .lineLimit(3...)
                .textFieldStyle(.todo_default)
                .multilineTextAlignment(.leading)
            TextField("edit_deadline", text: $formContract.deadlineDescription)
                .textFieldStyle(.todo_default)
                .disabled(true)
                .overlay(alignment: .trailing) {
                    Button(action: {
                        formContract.onShowDatePickerChanged(isShow: true)
                    }, label: {
                        Image(systemName: "calendar")
                    }).padding(.trailing, 32)
                }
            PhotosPicker(selection: $formContract.imageSelection, matching: .images) {
                GeometryReader { geo in
                    if let image = formContract.selectedImage {
                        Image(uiImage: image)
                            .resizable()
                            .scaledToFit()
                            .frame(width: geo.size.width * 0.6)
                            .frame(width: geo.size.width)
                    } else {
                        VStack {
                            Image(systemName: "photo.fill.on.rectangle.fill")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 100)
                            Text("edit_attachment_tips")
                                .foregroundStyle(.gray)
                                .font(.caption)
                        }.frame(width: geo.size.width, height: geo.size.height)
                    }
                }
            }
            Button(action: {
                // generated task for upstream
                formContract.onConfirmed(onTaskGenerated: { task in
                    onConfirmed(task)
                })
            }, label: {
                Text(confirmButtonText).frame(maxWidth: .infinity)
                    .textCase(.uppercase)
                    .frame(height: 40)
            }).padding(.horizontal)
                .buttonStyle(.todo_default)
        }.onAppear {
            if taskId != nil {
                formContract.fetch(taskId: taskId!)
            }
        }.errorAlert(
            error: Binding(
                get: { formContract.error },
                set: {value in
                    formContract.clearError()
                }
            )
        ).sheet(
            isPresented: Binding(
                get: {
                    formContract.showDatePicker
                },
                set: { value in
                    formContract.onShowDatePickerChanged(isShow: value)
                }
            )
        ) {
            VStack {
                DatePicker(
                    "edit_deadline",
                    selection: $formContract.deadline,
                    displayedComponents: [.date, .hourAndMinute]
                ).padding(.horizontal)
                    .datePickerStyle(.graphical)
                Button(action: {
                    formContract.onShowDatePickerChanged(isShow: false)
                }, label: {
                    Text("text_done").frame(maxWidth: .infinity)
                        .frame(height: 40)
                }).padding(.all)
                    .buttonStyle(.todo_default)
            }.presentationDetents([.fraction(0.7)])
        }
    }
}
