//
//  FloatingActionButton.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FloatingActionButton: View {
    let action: () -> Void
    let icon: String
    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                Button(action: action) {
                    Image(systemName: icon)
                        .font(.system(size: 25))
                        .foregroundColor(.white)
                }
                .frame(width: 60, height: 60)
                .background(Color("primary"))
                .cornerRadius(30)
                .shadow(radius: 10)
                .offset(x: -25, y: 10)
            }
        }
    }
}


struct FloatingActionButton_Previews: PreviewProvider {
    static var previews: some View {
        FloatingActionButton(action: {}, icon: "plus")
    }
}
