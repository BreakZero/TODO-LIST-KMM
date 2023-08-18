//
//  SignUpViewModel.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/9.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import data

extension SignUpScreen {
    @MainActor class ViewModel: ObservableObject {
        @Published private(set) var email: String = ""
        @Published private(set) var fullName: String = ""
        @Published private(set) var password: String = ""
        @Published private(set) var confirmPassword: String = ""
        @Published var error: Swift.Error? = nil
        
        func onEmailChanged(email: String) {
            self.email = email
        }
        
        func onFullNameChanged(fullName: String) {
            self.fullName = fullName
        }
        
        func onPasswordChanged(password: String) {
            self.password = password
        }
        
        func onConfirmPasswordChanged(password: String) {
            self.confirmPassword = password
        }
        
        func signUp() {
            if self.email.isEmpty {
                error = Error.EmailEmpty
                return
            }
            if self.fullName.isEmpty {
                error = Error.FullNameEmpty
                return
            }
            if self.password.isEmpty {
                error = Error.PasswordEmpty
                return
            }
            if self.password != self.confirmPassword {
                error = Error.PasswordNotMatch
                return
            }
            KoinManager.helper.insertOrUpdateUser(
                user: ModelUser(
                    uid: UUID().uuidString,
                    fullName: self.fullName,
                    email: self.email,
                    createAt: KoinManager.commonHelper.currentMilliseconds()
                ),
                completionHandler: { result, error in
                    print(result?.description ?? "")
                }
            )
        }
        
        enum Error: LocalizedError {
            case PasswordNotMatch
            case EmailEmpty
            case FullNameEmpty
            case PasswordEmpty
            
            var errorDescription: String? {
                switch self {
                case .PasswordNotMatch: return "Password not match, please try again"
                case .EmailEmpty: return "Email is empty"
                case .FullNameEmpty: return "FullName is empty"
                case .PasswordEmpty: return "Password is empty"
                }
            }
        }
    }
}
