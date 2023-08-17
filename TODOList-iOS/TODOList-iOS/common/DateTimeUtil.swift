//
//  DateTimeUtil.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/10.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


func currentTimeInMilliSeconds() -> Int64 {
    let currentDate = Date()
    let since1970 = currentDate.timeIntervalSince1970
    return Int64(since1970 * 1000)
}

extension Int64 {
    func asTimeStampToDateFormatted() -> String! {
        let timeSta: TimeInterval = Double(self)
        let date = Date(timeIntervalSince1970: (timeSta / 1000.0))
        return date.formatToString()
    }
    
    func asDate() -> Date {
        let timeSta: TimeInterval = Double(self)
        let date = Date(timeIntervalSince1970: (timeSta / 1000.0))
        return date
    }
}

private let dateFormatter = DateFormatter()
extension Date {
    func toMillis() -> Int64! {
        return Int64(self.timeIntervalSince1970 * 1000)
    }
    
    func formatToString(format: String = "y, MMM d, HH:mm") -> String {
        dateFormatter.dateFormat = format
        return dateFormatter.string(from: self)
    }
}
