//
//  NGAppDelegate.m
//  netcallmobile
//
//  Created by tom on 11/12/13.
//  Copyright (c) 2013 newgrand. All rights reserved.
//

#import "NGAppDelegate.h"

#import "NGViewController.h"
//#import "BPush.h"

@implementation NGAppDelegate

@synthesize window = _window;
@synthesize viewController = _viewController;

- (id)init
{
    NSHTTPCookieStorage* cookieStorage = [NSHTTPCookieStorage sharedHTTPCookieStorage];
    
    [cookieStorage setCookieAcceptPolicy:NSHTTPCookieAcceptPolicyAlways];
    
    self = [super init];
    return self;
}

- (void)createViewController
{
    NSAssert(!self.viewController, @"ViewController already created.");
    
    self.viewController = [[NGViewController alloc] init];
    self.viewController.wwwFolderName = @"www";
    self.viewController.startPage = @"index.html";
    
    // NOTE: To customize the view's frame size (which defaults to full screen), override
    // [self.viewController viewWillAppear:] in your view controller.
    
    self.window.rootViewController = self.viewController;
}

- (void)destroyViewController
{
    self.viewController = nil;
}

- (BOOL)application:(UIApplication*)application didFinishLaunchingWithOptions:(NSDictionary*)launchOptions
{
    CGRect screenBounds = [[UIScreen mainScreen] bounds];
    
    self.window = [[UIWindow alloc] initWithFrame:screenBounds];
    self.window.autoresizesSubviews = YES;
    
    // Create the main view on start-up only when not running unit tests.
    if (!NSClassFromString(@"CDVWebViewTest")) {
        [self createViewController];
    }
    
    [self.window makeKeyAndVisible];
    
    //push
//    [BPush setupChannel:launchOptions];
//    [BPush setDelegate:self];
    
    [application setApplicationIconBadgeNumber:0];
    [application registerForRemoteNotificationTypes:
     UIRemoteNotificationTypeAlert | UIRemoteNotificationTypeBadge| UIRemoteNotificationTypeSound];
    
    
    return YES;
}

- (void)applicationWillResignActive:(UIApplication*)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication*)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication*)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication*)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication*)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken{
    //[BPush registerDeviceToken:deviceToken];
    //[BPush bindChannel];
}

- (void) onMethod:(NSString*)method response:(NSDictionary*)data {
//    if ([BPushRequestMethod_Bind isEqualToString:method]) {
//        NSDictionary* res = [[NSDictionary alloc] initWithDictionary:data]; NSString *appid = [res valueForKey:BPushRequestAppIdKey];
//        NSString *userid = [res valueForKey:BPushRequestUserIdKey];
//        NSString *channelid = [res valueForKey:BPushRequestChannelIdKey];
//        int returnCode = [[res valueForKey:BPushRequestErrorCodeKey] intValue];
//        NSString *requestid = [res valueForKey:BPushRequestRequestIdKey];
//        NSURL* url = getPropertiesDirectoryFile(userid);
//        NSError * error=nil;
//        [userid writeToURL:url atomically:true encoding:NSUTF8StringEncoding error:&error];
//    }
}

- (void)application:(UIApplication *)application
didReceiveRemoteNotification:(NSDictionary *)userInfo {
    if(application.applicationState==UIApplicationStateBackground){
        //处理
    }
//    [BPush handleNotification:userInfo];
}
void uncaughtExceptionHandler(NSException *exception) {
    NSLog(@"CRASH: %@", exception);
    NSLog(@"Stack Trace: %@", [exception callStackSymbols]);
    // Internal error reporting
}

NSURL* getPropertiesDirectoryFile(NSString* fileName){
    NSString* propertiesDirectory = [[NSHomeDirectory() stringByAppendingPathComponent:@"Documents"] stringByAppendingPathComponent:@"properties"];
    
    NSFileManager *fileManager = [NSFileManager defaultManager];
    BOOL isDir = NO;
    BOOL isExist = [fileManager fileExistsAtPath:propertiesDirectory isDirectory:&isDir];
    if(!isExist||!isDir){
        [fileManager createDirectoryAtPath:propertiesDirectory withIntermediateDirectories:YES attributes:nil error:nil];
    }
    
    NSString* propertiesFile = [propertiesDirectory stringByAppendingPathComponent:fileName];
    return [NSURL fileURLWithPath:propertiesFile];
}

@end
