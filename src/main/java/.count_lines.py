import os
import json
import sys
import codecs

excluded_words = ['cmake', '/.', '.out', 'target/', 'doc/']

def get_sum_of_lines(path):
    files = os.listdir(path)
    if len(files) == 0:
        return 0
    sum_of_lines = 0
    for f in files:
        full_path = path + '/' + f
        if does_file_meet_conditions(full_path):
            if os.path.isdir(full_path):
                sum_of_lines += get_sum_of_lines(full_path)
            else:
                print(full_path)
                sum_of_lines += sum(1 for line in open(full_path))
    
    return sum_of_lines

def does_file_meet_conditions(path):
    for word in excluded_words:
        if word in path:
            return False
    return True

def is_on_windows():
    return os.name == 'nt'

#print(os.listdir('./util'))
number_of_lines = get_sum_of_lines('.')
print(number_of_lines)
