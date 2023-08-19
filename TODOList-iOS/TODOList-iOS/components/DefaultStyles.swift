//
//  DefaultTextField.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/9.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TodoDefaultTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<Self._Label>) -> some View {
        configuration
            .padding(.all)
            .overlay {
                RoundedRectangle(cornerRadius: 12)
                    .stroke(Color("primary"), lineWidth: 2)
            }
            .padding(.horizontal)
        //            .shadow(color: .gray, radius: 10)
    }
}

extension TextFieldStyle where Self == TodoDefaultTextFieldStyle {
    static var todo_default: Self {.init()}
}

struct TodoDefaultButtonStyle: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding(.vertical, 4)
            .background(Color("primary"))
            .foregroundStyle(.white)
            .clipShape(Capsule())
            .scaleEffect(configuration.isPressed ? 1.1 : 1)
            .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
    }
}

extension ButtonStyle where Self == TodoDefaultButtonStyle {
    static var todo_default: Self { .init() }
}
