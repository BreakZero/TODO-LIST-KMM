//
//  ColorExtesion.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI



extension Color {
    static func ofHex(hex: Int64, alpha: Double = 1) -> Color {
        return Color(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    static let appPrimary = Color("primary")
    static let appSecondary = Color("secondary")
    static let appOnSurface = Color("onSurface")
}
