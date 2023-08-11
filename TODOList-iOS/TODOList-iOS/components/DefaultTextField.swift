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
                    .stroke(Color.blue, lineWidth: 2)
            }
            .padding(.horizontal)
            .shadow(color: .gray, radius: 10)
    }
}

extension TextFieldStyle where Self == TodoDefaultTextFieldStyle {
    static var todo_default: Self {.init()}
}
