//
//  SettingsScreen.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SettingsScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    @StateObject private var viewModel = ViewModel()
    var body: some View {
        VStack {
            Spacer()
            Image("rafiki")
            Spacer()
            ProfileInfoItemView(key: "Full Name", value: viewModel.user?.fullName ?? "")
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            ProfileInfoItemView(key: "Email", value: viewModel.user?.email ?? "")
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            ProfileInfoItemView(key: "Password", value: "Change Password")
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            
            Button(action: {
                dismiss()
            }, label: {
                Text("LOG OUT")
                    .frame(maxWidth: .infinity)
                    .frame(height: 40)
            }).padding(.all)
            .buttonStyle(.borderedProminent)
        }
    }
}

struct ProfileInfoItemView: View {
    var key: String
    var value: String
    var body: some View {
        HStack {
            Text(key)
            Spacer()
            Text(value).foregroundColor(Color("primary"))
        }
    }
}
