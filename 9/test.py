import pyttsx3
import pandas as pd
from io import StringIO
import csv
from pydub import AudioSegment
engine = pyttsx3.init()
voices = engine.getProperty('voices')
english_id = voices[1].id
japanese_id = voices[2].id
k = [1,2,3,4,5,6]
for ka in k:
    read_file = pd.read_excel(r'Book{}.xlsx'.format(ka))
    read_file.to_csv (r'Book6.csv', index = None, header = True)

    fc = []
    with open('Book6.csv', encoding="utf8") as my_file:
        for line in my_file:
            fc.append(line)
        
    i=0
    for x in fc:
        y = x.split(",")
        engine.setProperty('voice', voices[2].id)
        engine.save_to_file(y[0], "book{}.mp3".format(i))
        i = i+1
        engine.setProperty('voice', voices[0].id)
        engine.save_to_file(y[1], "book{}.mp3".format(i))
        i = i+1
    engine.runAndWait()
    i = i-1
    j=0
    audio = AudioSegment.from_file("book0.mp3")
    while j < i:
        j=j+1
        sound = AudioSegment.from_file("book{}.mp3".format(j))
        silent = AudioSegment.silent(duration=1000)
        audio = audio + silent + sound
    file_handle = audio.export("combined{}.mp3".format(ka), format="mp3")
