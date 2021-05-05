/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <algorithm>
#include <chrono>
#include <iostream>
#include<vector>
#include <string.h>
#include "sstream"
using namespace std;
using namespace std::chrono;
using namespace std;
typedef std::basic_ostringstream<char> fcpstream;

static void numToStr(unsigned long long val, std::string* d) {
    char ty[30];
	  sprintf(ty, "%llu", val);
	  d->append(ty, strlen(ty));
}

int main()
{
    std::string a;
    auto start = high_resolution_clock::now();
    for(int i=0;i<100000;i++) {
        numToStr(i+100, &a);
    }
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop - start);
    cout << "numToStr = " << duration.count() << endl;
    //cout << a.length() << endl;
    
    std::string b;
    start = high_resolution_clock::now();
    for(int i=0;i<100000;i++) {
        b.append(to_string(i+100));
    }
    stop = high_resolution_clock::now();
    duration = duration_cast<microseconds>(stop - start);
    cout << "to_string = " << duration.count() << endl;
    //cout << b.length() << endl;

    fcpstream f;
    start = high_resolution_clock::now();
    for(int i=0;i<100000;i++) {
        f << (i+100);
    }
    stop = high_resolution_clock::now();
    f.str();
    duration = duration_cast<microseconds>(stop - start);
    cout << "sstream = " << duration.count() << endl;
    //cout << f.str().length() << endl;

    return 0;
}
