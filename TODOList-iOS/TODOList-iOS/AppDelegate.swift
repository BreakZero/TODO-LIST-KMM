//
//  AppDelegate.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/8/19.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

@available(iOS 13.0, *)
private func customNavBarAppearance() -> UINavigationBarAppearance {
    let customNavBarAppearance = UINavigationBarAppearance()
    
    // Apply a red background.
    customNavBarAppearance.configureWithOpaqueBackground()
//    customNavBarAppearance.backgroundColor = .systemRed
    
    // Apply white colored normal and large titles.
//    customNavBarAppearance.titleTextAttributes = [.foregroundColor: UIColor.white]
//    customNavBarAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor.white]

    // Apply white color to all the nav bar buttons.
    let barButtonItemAppearance = UIBarButtonItemAppearance(style: .plain)
    barButtonItemAppearance.normal.titleTextAttributes = [.foregroundColor: UIColor(.appPrimary)]
    barButtonItemAppearance.disabled.titleTextAttributes = [.foregroundColor: UIColor.lightText]
    barButtonItemAppearance.highlighted.titleTextAttributes = [.foregroundColor: UIColor.label]
    barButtonItemAppearance.focused.titleTextAttributes = [.foregroundColor: UIColor.white]
    
    customNavBarAppearance.buttonAppearance = barButtonItemAppearance
    customNavBarAppearance.backButtonAppearance = barButtonItemAppearance
    customNavBarAppearance.doneButtonAppearance = barButtonItemAppearance
    
    return customNavBarAppearance
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
//        let newNavBarAppearance = customNavBarAppearance()
//        
//        let appearance = UINavigationBar.appearance()
//        appearance.scrollEdgeAppearance = newNavBarAppearance
//        appearance.compactAppearance = newNavBarAppearance
//        appearance.standardAppearance = newNavBarAppearance
//        if #available(iOS 15.0, *) {
//            appearance.compactScrollEdgeAppearance = newNavBarAppearance
//        }
        
        return true
    }
}
