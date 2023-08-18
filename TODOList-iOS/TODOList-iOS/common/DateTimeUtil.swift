//
//  DateTimeUtil.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


func currentTimeInMilliSeconds() -> Int64 {
    let since1970 = Date.now.timeIntervalSince1970
    return Int64(since1970 * 1000)
}

extension Date {
    func toMillis() -> Int64! {
        return Int64(self.timeIntervalSince1970 * 1000)
    }
}
