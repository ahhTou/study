#include <bits/stdc++.h>
using namespace std;

int main()
{
    vector<int> nums = {0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    int a[nums.size()] = {0};
    for (int i = 0; i < nums.size(); i++)
    {
        a[nums[i]]++;
        if (a[nums[i]] > 1)
        {
            return nums[i];
        }
    }
    return 0;
}