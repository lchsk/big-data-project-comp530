# -*- coding: utf-8 -*-

import os
from sqlite3 import dbapi2 as sqlite3
from flask import Flask, request, session, g, redirect, url_for, abort, \
     render_template, flash


# create our little application :)
app = Flask(__name__)
app.debug = True

@app.route('/')
def index():

    f = open('data/train.csv')

    train = []

    for line in f:
        d = line.split(',')

        if d[0] == '1':
            train.append(d)

    f.close()

    dirs = os.listdir('data')
    data = []

    for _dir in dirs:
        if 'test_' in _dir and '_output' in _dir:

            f = open('data/' + _dir)

            for line in f:
                d = line.split(',')
                if d[0] != 'e':
                    data.append(d)

            f.close()

    return render_template('map.html', data=data, train=train[1:])




if __name__ == "__main__":
    app.run()
