#include <bits/stdc++.h>
using namespace std;

int main()
{
    vector<int> nums1 = {2, 3, 1, 0, 2, 5, 3};
    for (int a = 0; a < nums1.size() - 1; a++)
    {
        int goal = nums1[a];
        for (int i = 0; i < nums1.size() - 1; i++)
        {
            /* code */
            if (a == i)
                break;
            if (goal == nums1[0])
            {
                return goal;
            }
        }
    }
    return 0;
}
