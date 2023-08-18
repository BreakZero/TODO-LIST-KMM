//
//  DateTimeUtil.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

extension Date {
    func toEpochMillis() -> Int64! {
        return Int64(self.timeIntervalSince1970 * 1000)
    }
}
