//
//  main.cpp
//  kmp
//
//  Created by Yifu Zhou on 16/7/26.
//  Copyright © 2016年 Yifu. All rights reserved.
//


#include<stdio.h>
#include<vector>
#include<queue>
#include<string.h>
#define maxn 250
#define INF 0X3F3F3F3F
using namespace std;

int N;
int A[maxn];
char MAP[maxn][maxn];
bool ib[maxn];

struct Edge
{
    int from,to,cap,flow;
    Edge(int u,int v,int c,int f):from(u),to(v),cap(c),flow(f){}
};


struct EdmondsKarp
{
    int n,m;
    vector<Edge>edges;
    vector<int>G[maxn];
    int a[maxn];
    int p[maxn];
    
    void init(int n)
    {
        this->n=n;
        for(int i=0;i<n;i++) G[i].clear();
        edges.clear();
    }
    
    void AddEdge(int from,int to,int cap)
    {
        edges.push_back(Edge(from,to,cap,0));
        edges.push_back(Edge(to,from,0,0));
        m=edges.size();
        G[from].push_back(m-2);
        G[to].push_back(m-1);
    }
    
    int Maxflow(int s,int t)
    {
        int flow=0;
        for(;;)
        {
            memset(a,0,sizeof(a));
            queue<int>Q;
            Q.push(s);
            a[s]=INF;
            while(!Q.empty())
            {
                int x=Q.front();Q.pop();
                for(unsigned int i=0;i<G[x].size();i++)
                {
                    Edge& e=edges[G[x][i]];
                    if(!a[e.to]&&e.cap>e.flow)
                    {
                        p[e.to]=G[x][i];
                        a[e.to]=min(a[x],e.cap-e.flow);
                        Q.push(e.to);
                    }
                }
                if(a[t]) break;
            }
            if(!a[t]) break;
            for(int u=t;u!=s;u=edges[p[u]].from)
            {
                edges[p[u]].flow+=a[t];
                edges[p[u]^1].flow-=a[t];
            }
            flow+=a[t];
        }
        return flow;
    }
    
    int Build(int val)
    {
        int ans=0;
        memset(ib,0,sizeof(ib));
        init(2*N+2);
        for(int i=1;i<=N;i++)
        {
            if(!A[i]) continue;
            AddEdge(0,i,A[i]);
            AddEdge(i,i+N,A[i]);
            for(int j=1;j<=N;j++)
                if(MAP[i][j]=='Y')
                {
                    if(!A[j]) ib[i]=true;
                    else AddEdge(i,j+N,INF);
                }
        }
        for(int i=1;i<=N;i++)
            if(ib[i]) {AddEdge(i+N,n-1,val);ans+=val;}
            else if(A[i]){AddEdge(i+N,n-1,1);ans++;};
        return ans;
    }
    
    void solve()
    {
        int a,b,ans = 0;
        int l=0,r=10010;
        while(l<r)
        {
            int mid=(l+r)>>1;
            a=Build(mid);
            b=Maxflow(0,n-1);
            if(a==b)
            {
                l=mid+1;
                ans=mid;
            }
            else r=mid;
        }
        printf("%d\n",ans);
    }
};

int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d",&N);
        for(int i=1;i<=N;i++) scanf("%d",&A[i]);
        for(int i=1;i<=N;i++) scanf("%s",MAP[i]+1);
        EdmondsKarp EK;
        EK.solve();
    }
    return 0;
}
