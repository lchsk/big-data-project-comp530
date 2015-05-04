import pandas as pd
import statsmodels.api as sm
import numpy as np
import matplotlib.pyplot as pl

class Test(object):
    def __init__(self, settings):
        self.settings = settings

        self.train = pd.read_csv("train.csv")
        self.train.columns = ["arch", "height", "distM", "distP", "distB", "countM", 'countP', 'countB']
        self.train['intercept'] = 1.0
        self.train_cols = ['height', 'distB', 'distM', 'distP', 'countP']

        self.test = pd.read_csv(self.settings.params['test_set'])

    def run(self):
        model = sm.Logit(self.train['arch'], self.train[self.train_cols])
        result = model.fit()
        self.test['pred'] = result.predict(self.test[self.train_cols])
        print result.summary()

        self.save_output()

    def save_output(self):

        # path = self.test.columns
        # print path

        self.test.to_csv(self.settings.params['test_set'] + '_output', index=False)

        # output = open('output_' + self.settings.params['test_set'], 'w')

        # output.write(self.test)

        # output.close()
