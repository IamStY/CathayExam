# CathayExam


1. 架構 - MVVM + LiveData + RoomDatabase + Repository
2. 資料落地, 首次儲存後支援離線 , 資料全部拆解後放到Room Db
---------------------------
 call flow  
 Room query -> liveData notify ui-> api request(read file) -> api response(read file)
 -> insert data list -> Room Live Data Notify UI -> data synced
 --------------------------
3. animations and ui enhancements
4. coroutine
5. VMFactory + OOP化 ViewModel
6. animations and ui enhancements
7. landscape / portrait OK!


收到考題日期 12-07(一)
開始作答日期 12-08(二)
最後更新日期12-09(三)
