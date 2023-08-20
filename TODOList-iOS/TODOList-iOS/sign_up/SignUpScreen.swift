//
//  SignUpScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/8.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SignUpScreen: View {
    enum Field: Hashable {
        case email
        case fullName
        case password
        case confirmPassword
    }
    
    @StateObject private var viewModel = ViewModel()
    @FocusState private var focusedField: Field?
    var body: some View {
        VStack {
            Spacer()
            Image("logo")
            Spacer()
            TextField("sign_in_up_email", text: Binding(
                get: {viewModel.email},
                set: {viewModel.onEmailChanged(email: $0)})
            ).focused($focusedField, equals: .email)
                .submitLabel(.next)
                .onSubmit({
                    focusedField = .fullName
                })
                .textFieldStyle(.todo_default)
            
            TextField("sign_in_up_full_name", text: Binding(
                get: {viewModel.fullName},
                set: {viewModel.onFullNameChanged(fullName: $0)})
            ).focused($focusedField, equals: .fullName)
                .submitLabel(.next)
                .onSubmit {
                    focusedField = .password
                }
                .textFieldStyle(.todo_default)
            TextField("sign_in_up_password", text: Binding(
                get: {viewModel.password},
                set: {viewModel.onPasswordChanged(password: $0)})
            ).focused($focusedField, equals: .password)
                .submitLabel(.next)
                .onSubmit {
                    focusedField = .confirmPassword
                }
                .textFieldStyle(.todo_default)
            TextField("sign_in_up_confirm_password", text: Binding(
                get: {viewModel.confirmPassword},
                set: {viewModel.onConfirmPasswordChanged(password: $0)})
            ).focused($focusedField, equals: .confirmPassword)
                .submitLabel(.done)
                .textFieldStyle(.todo_default)
            Button(action: {
                viewModel.signUp()
            }, label: {
                Text("sign_in_up_up")
                    .textCase(.uppercase)
                    .frame(maxWidth: .infinity)
                    .frame(height: 40)
            }).padding(.all)
                .buttonStyle(.todo_default)
        }.errorAlert(error: $viewModel.error)
    }
}

struct SignUp_Preview: PreviewProvider {
    static var previews: some View {
        SignUpScreen()
    }
}
