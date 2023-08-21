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
    
    static let shared = UserManager()
    
    override init() {
        IOSHelperKt.doInitKoin()
        super.init()
        KoinManager.helper.checkUser(
            onEach: {  user in
                self.loggedUser = user
            },
            onComplete: {},
            onThrow: { error in }
        )
    }
    
    func insertUser(user: ModelUser) {
        KoinManager.helper.insertOrUpdateUser(user: user, completionHandler: { result, error in
            debugPrint("inser result: \(String(describing: result?.boolValue))")
            debugPrint("inser error: \(String(describing: error?.localizedDescription))")
        })
    }
}
