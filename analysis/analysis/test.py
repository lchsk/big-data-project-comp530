import pandas as pd
import statsmodels.api as sm
import numpy as np
import matplotlib.pyplot as pl
import os

class Test(object):
    def __init__(self, settings):
        self.settings = settings

        self.train = pd.read_csv("train.csv")
        self.train.columns = ["arch", "height", "distM", "distP", "distB", "countM", 'countP', 'countB', 'e','n','lat','lon']
        self.train['intercept'] = 1.0
        self.train_cols = ['height', 'distB', 'distM', 'distP', 'countP']

        self.test = {}

        if self.settings.params['test_set'] == 'all':
            dirs = os.listdir('data')

            for _d in dirs:
                if 'test_' in _d and 'output' not in _d:
                    self.test[_d] = pd.read_csv('data/' + _d)
        else:
            self.test[self.settings.params['test_set']] = pd.read_csv(self.settings.params['test_set'])

    def run(self):

        model = sm.Logit(self.train['arch'], self.train[self.train_cols])
        result = model.fit()

        for k, v in self.test.iteritems():
            v['pred'] = result.predict(v[self.train_cols])
            print result.summary()

            self.save_output()

    def save_output(self):

        # path = self.test.columns
        # print path

        for k, v in self.test.iteritems():
            v.to_csv(k + '_output', index=False)

        # output = open('output_' + self.settings.params['test_set'], 'w')

        # output.write(self.test)

        # output.close()
