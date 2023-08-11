//
//  TaskCategory.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum TaskCategory: CaseIterable {
    case WORK
    case LIFE
    case GAMING
}

func getCategory(category: TaskCategory) -> Int64 {
    return switch category {
    case .WORK: 0xFFF79E89
    case .LIFE: 0xFFF76C6A
    case .GAMING: 0xFFF58B70
    }
}
