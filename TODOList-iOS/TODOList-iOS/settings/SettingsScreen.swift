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
            ProfileInfoItemView(key: String(localized: "settings_full_name"), value: viewModel.user?.fullName ?? "")
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            ProfileInfoItemView(key:  String(localized: "settings_email"), value: viewModel.user?.email ?? "")
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            ProfileInfoItemView(key:  String(localized: "settings_password"), value:  String(localized: "settings_change_password"))
                .padding(.horizontal, 16)
                .padding(.vertical, 4)
            
            Button(action: {
                dismiss()
            }, label: {
                Text("settings_logout")
                    .textCase(.uppercase)
                    .frame(maxWidth: .infinity)
                    .frame(height: 40)
            }).padding(.all)
                .buttonStyle(.todo_default)
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
            Text(value).foregroundColor(Color.appPrimary)
        }
    }
}

struct SettingsScreen_Preview: PreviewProvider {
    static var previews: some View {
        SettingsScreen()
    }
}
