//
//  UserManager.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/8.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import data

class UserManager: NSObject, ObservableObject {
    @Published var loggedUser: ModelUser? = nil
    private let userRepository = KoinManager.userRepository
    
    static let shared = UserManager()
    
    override init() {
        IOSHelperKt.doInitKoin()
        super.init()
        userRepository.checkUser(
            onEach: {  user in
                self.loggedUser = user
            },
            onComplete: {},
            onThrow: { error in }
        )
    }
}
