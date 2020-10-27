def replaceSpace(s: str) -> str:
    res = ''
    for a_str in s:
        if a_str == ' ':
            res += '%20'
        else:
            res += a_str
    return res


def replaceSpace2(s: str) -> str:
    return s.replace(' ', '%20')


a = replaceSpace2("we are happy")
print(a)
