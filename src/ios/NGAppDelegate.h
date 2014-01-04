//
//  NGAppDelegate.h
//  netcallmobile
//
//  Created by tom on 11/12/13.
//  Copyright (c) 2013 newgrand. All rights reserved.
//

#import <UIKit/UIKit.h>

@class NGViewController;

@interface NGAppDelegate : UIResponder <UIApplicationDelegate>
@property (strong, nonatomic) UIWindow* window;

@property (strong, nonatomic) NGViewController* viewController;

- (void)createViewController;
- (void)destroyViewController;

@end
