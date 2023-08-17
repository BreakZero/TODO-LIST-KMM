//
//  TodoTaskEditScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import PhotosUI
import UIKit

struct TodoTaskEditScreen: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = ViewModel()
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            Spacer().frame(height: 32)
            TextField("Title", text: $viewModel.title)
                .textFieldStyle(.todo_default)
            TextField("Description", text: $viewModel.description, axis: .vertical)
                .lineLimit(3...)
                .textFieldStyle(.todo_default)
                .multilineTextAlignment(.leading)
            TextField("Deadline", text: $viewModel.deadlineDescription)
                .textFieldStyle(.todo_default)
                .disabled(true)
                .overlay(alignment: .trailing) {
                    Button(action: {
                        viewModel.onShowDatePickerChanged(isShow: true)
                    }, label: {
                        Image(systemName: "calendar")
                    }).padding(.trailing, 32)
                }
            PhotosPicker(selection: $viewModel.imageSelection, matching: .images) {
                GeometryReader { geo in
                    if let image = viewModel.selectedImage {
                        Image(uiImage: image)
                            .resizable()
                            .scaledToFit()
                            .frame(width: geo.size.width * 0.6)
                            .frame(width: geo.size.width)
                    } else {
                        Image(systemName: "star")
                            .resizable()
                            .scaledToFit()
                            .frame(width: geo.size.width * 0.6)
                            .frame(width: geo.size.width, height: geo.size.height)
                    }
                }
            }
            Button(action: {
                viewModel.insertTask {
                    dismiss()
                }
            }, label: {
                Text("ADD TODO").frame(maxWidth: .infinity)
                    .frame(height: 40)
            }).padding(.horizontal)
                .buttonStyle(.borderedProminent)
        }.errorAlert(
            error: Binding(
                get: { viewModel.error },
                set: {value in
                    viewModel.clearError()
                }
            )).sheet(isPresented: Binding(
                get: {
                    viewModel.showDatePicker
                },
                set: { value in
                    viewModel.onShowDatePickerChanged(isShow: value)
                }
            ), content: {
                VStack {
                    DatePicker(
                        "Deadline",
                        selection: $viewModel.deadline,
                        displayedComponents: [.date, .hourAndMinute]
                    ).datePickerStyle(.graphical)
                    Button(action: {
                        viewModel.onShowDatePickerChanged(isShow: false)
                    }, label: {
                        Text("DONE").frame(maxWidth: .infinity)
                            .frame(height: 40)
                    }).padding(.all)
                        .buttonStyle(.borderedProminent)
                }
            })
    }
}
