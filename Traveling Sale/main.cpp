//
//  main.cpp
//  Traveling Sale
//
//  Created by Yifu Zhou on 16/8/6.
//  Copyright © 2016年 Yifu. All rights reserved.
//


/* 主题：近似算法——旅行售货员问题
 * 作者：chinazhangjie
 * 邮箱：chinajiezhang@gmail.com
 * 开发语言： C++
 * 开发环境： Virsual Studio 2005
 * 时间: 2010.12.06
 */

#include <iostream>
#include <vector>
#include <limits>
using namespace std ;

struct TreeNode
{
public:
    TreeNode (int _VI_A = 0, int _VI_B = 0, int _Weight = 0) //init the value in tree
    :VI_A (_VI_A),
    VI_B (_VI_B),
    Weight (_Weight)
    { }
public:
    int VI_A;
    int VI_B;
    int Weight;
};

class Prim_MST
{
public:
    //init the algorithm of Prim. What we want to get is the MST after the Prim algorithm
    Prim_MST ()
    {}
    Prim_MST (const vector<vector<int>>& vnGraph)
    {
        m_nvGraph = vnGraph ;
        m_nNodeCount = (int)m_nvGraph.size ();
    }
    void SetData (const vector<vector<int> >& vnGraph)
    {
        m_nvGraph = vnGraph ;
        m_nNodeCount = (int)m_nvGraph.size ();
    }
    const vector<TreeNode>& GetMSTree () const
    {
        return m_tnMSTree ;
    }
    const vector<vector<int> >& GetGraph () const
    {
        return    m_nvGraph ;
    }
    void Prim ()
    {
        //set they all unvisited.
        vector<bool> visited (m_nNodeCount, false) ;
        visited[0] = true ;
        
        int nMaxIndexA = 0;
        int nMaxIndexB = 0;
        int j = 0 ;
        while (j < m_nNodeCount - 1) {
            int nMaxWeight = numeric_limits<int>::max () ;
            // find the shorest path in current path
            int i = 0 ;
            while (i < m_nNodeCount) {
                if (!visited[i]) {
                    ++ i ;
                    continue ;
                }
                for (int j = 0; j < m_nNodeCount; ++ j) {
                    if (!visited[j] && nMaxWeight > m_nvGraph[i][j]) {
                        nMaxWeight = m_nvGraph[i][j] ;
                        nMaxIndexA = i ;
                        nMaxIndexB = j ;
                    }
                }
                ++i ;
            }
            visited[nMaxIndexB] = true ;
            m_tnMSTree.push_back (TreeNode(nMaxIndexA, nMaxIndexB, nMaxWeight)) ;
            ++ j ;
        }
        // output solution of Prim
//        for (vector<TreeNode>::const_iterator ite = m_tnMSTree.begin() ;
//         ite != m_tnMSTree.end() ;
//         ++ ite ) {
//         cout << (*ite).m_nVertexIndexA << "->"
//         << (*ite).m_nVertexIndexB << " : "
//         << (*ite).m_nWeight << endl ;
//         }
    }
    
private:
    vector<vector<int>> m_nvGraph ;    // undirected graph
    vector<TreeNode>m_tnMSTree ;    // minimun spanning tree
    int    m_nNodeCount ;
} ;


class TSP_Approx
{
public:
    TSP_Approx (const vector<vector<int> >& vnGraph)
    {
        m_mstPrim.SetData(vnGraph) ;
    }
    void Get_Approx_Path ()
    {
        m_mstPrim.Prim () ;
        vector<TreeNode>mstree = m_mstPrim.GetMSTree () ;
        vector<vector<int> >graph = m_mstPrim.GetGraph() ;
        int iweight = 0 ;
        for (vector<TreeNode>::const_iterator ite = mstree.begin() ;
             ite != mstree.end() ;
             ++ ite ) {
            cout << (*ite).VI_A << "->"
            << (*ite).VI_B << " : "
            << (*ite).Weight << endl;
            iweight += (*ite).Weight;
        }
        cout << mstree[mstree.size()-1].VI_B << "->"
        << mstree[0].VI_A << " : "
        << graph[mstree[0].VI_A][mstree[mstree.size()-1].VI_B]
        << endl ;
        iweight += graph[mstree[0].VI_A][mstree[mstree.size()-1].VI_B] ;
        cout << "Total weight: " << iweight  << endl ;
    }
private:
    Prim_MST m_mstPrim ;
};
int main()
{
    const int cnNodeCount = 5 ;
    vector<vector<int> > graph (cnNodeCount);
    for (size_t i = 0; i < graph.size(); ++ i){
        graph[i].resize (cnNodeCount, numeric_limits<int>::max()) ;
    }
    graph[0][1] = 5 ;
    graph[0][2] = 1 ;
    graph[0][3] = 2 ;
    graph[0][4] = 3 ;
    
    graph[1][0] = 5 ;
    graph[1][2] = 4 ;
    graph[1][3] = 2 ;
    graph[1][4] = 2 ;
    
    graph[2][1] = 4 ;
    graph[2][0] = 1 ;
    graph[2][3] = 5 ;
    graph[2][4] = 3 ;
    
    graph[3][1] = 2 ;
    graph[3][2] = 5 ;
    graph[3][0] = 2 ;
    graph[3][4] = 2 ;
    
    graph[4][1] = 2 ;
    graph[4][2] = 3 ;
    graph[4][3] = 2 ;
    graph[4][0] = 3 ;
    
    TSP_Approx aa (graph) ;
    aa.Get_Approx_Path () ;
    return 0 ;
}