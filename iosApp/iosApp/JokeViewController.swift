//
//  JokeViewController.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 08.05.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import UIKit
import shared

class JokeViewController: UIViewController {
    @IBOutlet private var jokeText: UITextView!
    
    private var viewModel: JokeViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel = JokeViewModel()
        
        jokeText.bindText(liveData: viewModel.jokeText)
    }
    
    @IBAction private func onRandomJokePressed() {
        viewModel.onRandomJokePressed()
    }
}
