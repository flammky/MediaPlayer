# MediaPlayer

Simple MediaPlayer App that able to Scan &amp; Play local playable Media. Using Exoplayer with Dagger-hilt &amp; ViewModels

Not Ready yet, but can do Simple things.

> - If you want to clone this simply remove the BuildConfig check in `MPApplication`
> - ~~Audio should be inside the `Music` or Folder Generated by the OS~~ Android 29 / Q only
> - Audio Images are fetched from the Album Art and not the one embedded in the files. `Might support this in the future with ID3v2`
> - Android Nougat 7.0 / API 24 and above

Features :
- Foreground Service on Background & Lock (tested 29 & 30 only)
- Playlist From Album & Artist row in `Home` 
- MediaStore Audio Files Scan
- ~~Simple Playback with Play/Pause~~ Notification Controller
- Folder Path
- Viewpager display currently playing song along with Album Images

Working On:
- Artist & Album Fragment
- Better Transport Control
- Exoplayer Services with MediaSessionCompat
- Fragment Controller  
- MediaMetadataCompat  
- MediaServiceBrowserCompat
- PlayerNotificationManager
- Better UI / UX


Plans :
- History, Favorite, Playlist, Album Image Changer with Room & DataStore. `> ID3v2 If Possible`
- Video Player support with                                               `> PiP, Brightness by Scroll, Volume by Scroll, Seekbar Preview`
- Media Streaming with Firebase.                                          `> Got Firebase Storage Limit so might be just Downloadable Instead`
- Google & Others Login handled by Firebase.                              `> Might add Local Account but most likely not `
- Custom Theming

Credits :
- Building feature-rich media apps with ExoPlayer (Google I/O '18)
- Uamp
- Spotify Clone
- Music-Player-GO
- RetroMusicPlayer
- Flaticon

| Dark Mode | Light Mode |
| -------------- | -------------- |
|<img src="https://user-images.githubusercontent.com/94031495/151054755-05079b03-72ff-42e1-873d-cdc70303cd95.png" width="250">|<img src="https://user-images.githubusercontent.com/94031495/151054781-ea9c0a5d-28b8-4865-9024-3a8302161f6d.png" width="250">|
|<img src="https://user-images.githubusercontent.com/94031495/151903094-fb9e8671-eab2-4bb1-8473-22b8b541e5ef.png" width="250"> | <img src="https://user-images.githubusercontent.com/94031495/151903100-0605a4b6-b9cc-4106-8626-7e06f6044a87.png" width="250"> |


Thank you For Visiting ^_^\
· I'm Usually on https://discord.gg/programming if you have question
