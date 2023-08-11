//
//  SettingsViewModel.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import data

extension SettingsScreen {
    @MainActor class ViewModel: ObservableObject {
        @Published private(set) var user: ModelUser? = nil
        
        init() {
            KoinManager.helper.checkUser(
                onEach: {user in
                    self.user = user
                },
                onComplete: {},
                onThrow: {error in }
            )
        }
    }
}
