//
//  NGSpeech.h
//  netcallmobile
//
//  Created by tom on 11/14/13.
//  Copyright (c) 2013 newgrand. All rights reserved.
//

#import "CDVPlugin.h"
#import "XIAudio.h"

@interface NGSpeech : CDVPlugin <XIRecordAudioDelegate>{
    XIAudio *_mXIAudio;
    NSString *_mplayStatusFunName;
    NSString *_mrecordStatusFunName;
}
- (void)startRecord:(CDVInvokedUrlCommand*)command;
- (void)stopRecord:(CDVInvokedUrlCommand*)command;
- (void)play:(CDVInvokedUrlCommand*)command;
- (void)stopPlay:(CDVInvokedUrlCommand*)command;
- (void)setCancel:(CDVInvokedUrlCommand*)command;
-(void)renameSpeechFile:(CDVInvokedUrlCommand*)command;
@end
