from analysis.settings import Settings
from analysis.train import TrainingEvaluation
from analysis.test import Test

if __name__ == '__main__':

    settings = Settings()
    settings.read_parameters()
    # settings.print_parameters()

    if settings.params['evaluation']:
        print '\nRunning evaluation...'
        print '*' * 50 + '\n'

        t = TrainingEvaluation()
        t.evaluate()
    elif settings.params['testing']:
        print '\nGenerating test data...'
        print '*' * 50 + '\n'

        t = Test(settings)
        t.run()
