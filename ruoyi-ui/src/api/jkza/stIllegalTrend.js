import request from '@/utils/request'

// 查询非法趋势列表
export function listStIllegalTrend(query) {
  return request({
    url: '/jkza/stIllegalTrend/list',
    method: 'get',
    params: query
  })
}

// 查询非法趋势详细
export function getStIllegalTrend(id) {
  return request({
    url: '/jkza/stIllegalTrend/' + id,
    method: 'get'
  })
}

// 新增非法趋势
export function addStIllegalTrend(data) {
  return request({
    url: '/jkza/stIllegalTrend',
    method: 'post',
    data: data
  })
}

// 修改非法趋势
export function updateStIllegalTrend(data) {
  return request({
    url: '/jkza/stIllegalTrend',
    method: 'put',
    data: data
  })
}

// 删除非法趋势
export function delStIllegalTrend(id) {
  return request({
    url: '/jkza/stIllegalTrend/' + id,
    method: 'delete'
  })
}
