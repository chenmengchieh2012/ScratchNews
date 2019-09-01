import jieba
import sys, io
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
argtext=sys.argv[1]
def segText(text):
    seg_list = jieba.cut(text)
    return seg_list
_list = segText(argtext)
for i in _list:
    print(i)
