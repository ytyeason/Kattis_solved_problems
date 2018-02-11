#include <iostream>
#include <cstring>
using namespace std;

void prefixMatrix(string pat, int *lps){
    int len = 0, i = 1;
    lps[0] = 0;
    while (i < pat.size()){
       if (pat[i] == pat[len]){
         len = len+1;
         lps[i] = len;
         i = i+1;
       }
       else{
         if (len != 0) len = lps[len-1];//if not match got to the last char, and find the lps, no increment for i here
         else lps[i] = 0, i++;//if it zero, then only increment i
       }
    }
}

void KMP(string pat, string txt){
    int M = pat.size(), N = txt.size();
    int lps[M];
    int j  = 0 , i = 0;

    prefixMatrix(pat, lps);

    while (i < N){
      if (pat[j] == txt[i]){//if char match here
        i++;
        j++;
      }
      if (j == M){//if full pat match here
        cout << i-j << " ";
        j = lps[j-1];
      }
      else if (i < N && pat[j] != txt[i]){//if no match, go the the previous lps
        if (j != 0) {
          j = lps[j-1];
        }
        else {
          i++;
        }
      }
    }
    cout << endl;
}

int main(){
    string pat , txt;
    while (getline(cin,pat) && getline(cin,txt)) {
      KMP(pat,txt);
    }

    return 0;
}
