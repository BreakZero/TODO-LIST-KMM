//
//  TodoTaskEditScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TodoTaskEditScreen: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = ViewModel()
    
    var body: some View {
        VStack {
            Spacer().frame(height: 32)
            TextField("Title", text: $viewModel.title)
                .textFieldStyle(.todo_default)
            TextField("Description", text: $viewModel.description)
                .textFieldStyle(.todo_default)
                .multilineTextAlignment(.leading)
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
        ))
    }
}
