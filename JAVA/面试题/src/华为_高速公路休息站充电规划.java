public class 华为_高速公路休息站充电规划 {
    public static void main(String[] args) {

    }
}
/**
// we have defined the necessary header files here for this problem.
// If additional header files are needed in your program, please import here.
using PII = pair<int, int>;
int main()
{
// please define the C++14 input here. For example: int a,b; cin>>a>>b;;
// please finish the function body here.
// please define the C++14 output here. For example:cout<<____<<endl;
int D;
cin >> D;
int n;
cin >> n;
vector<int>dis;
vector<int>val;
dis.push_back(0);
val.push_back(0);
for (int i = 0; i < n; i++) {
int a, b;
cin >> a >> b;
dis.push_back(a);
val.push_back(b);
}
vector<int>dp(n + 2);
for (int i = 0; i < dp.size(); i++) {
dp[i] = INT16_MAX;
}
dp[0] = 0;
for (int i = 0; i < dis.size(); i++) {
for (int j = i - 1; j >= 0; j--) {
if (dis[i] - dis[j] > 1000) break;

dp[i] = min(dp[i], dp[j] + val[i] + 1);
}
}

int res = INT16_MAX;
for (int i = n - 1; i >= 0; i--) {
if (D - dis[i] > 1000) break;
// cout << dp[i] << endl;
res = min(res, dp[i]);
}

if (D <= 1000) {
cout << D / 100 << endl;
return 0;
}
if (res == INT16_MAX) {
cout << -1 << endl;
return 0;
}
res = res + (D / 100);

cout << res << endl;
return 0;
}
 */