//
//  AppTopbar.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppTopbar<Destination>: View where Destination: View {
    var destination: Destination
    
    init(@ViewBuilder desination: () -> Destination) {
        self.destination = desination()
    }
    
    var body: some View {
        HStack {
            Text("TODO LIST")
                .font(.system(size: 24))
                .bold()
                .foregroundStyle(Color("secondary"))
            Spacer()
            NavigationLink(
                destination: self.destination,
                label: {
                    Image("settings")
                        .renderingMode(.template)
                        .foregroundColor(Color("tertiary"))
                }
            )
        }.padding(.horizontal, 16.0)
    }
}
