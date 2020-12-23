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
    
struct RegisterView: View {
    var body: some View {
        Text("Register")
    }
}
    
struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack{
                NavigationLink(destination: LoginView()) {
                    Text("Login")
                }
                
                NavigationLink(destination: RegisterView()) {
                    Text("Register")
                }
            }
        .navigationBarTitle("Diaspora")
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


