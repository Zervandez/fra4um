//
//  ContentView.swift
//  fra4um
//
//  Created by Ariel Halilaj on 22.12.20.
//  Copyright © 2020 Ariel Halilaj. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    
    var body: some View {
        Text("Login")
    }
    
    }

struct ContentView: View {
    var body: some View {
        //Text("Hello, World!")
        VStack {
            Button(action: {
                print("Regster btn clicked")
            }) {
                Text("Register")
            }
            
            NavigationLink(destination: LoginView()) {
                Text("Login")
            }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


