import request from '@/utils/request'

// 查询容量排名列表
export function listStCapacityRank(query) {
  return request({
    url: '/jkza/stCapacityRank/list',
    method: 'get',
    params: query
  })
}

// 查询容量排名详细
export function getStCapacityRank(id) {
  return request({
    url: '/jkza/stCapacityRank/' + id,
    method: 'get'
  })
}

// 新增容量排名
export function addStCapacityRank(data) {
  return request({
    url: '/jkza/stCapacityRank',
    method: 'post',
    data: data
  })
}

// 修改容量排名
export function updateStCapacityRank(data) {
  return request({
    url: '/jkza/stCapacityRank',
    method: 'put',
    data: data
  })
}

// 删除容量排名
export function delStCapacityRank(id) {
  return request({
    url: '/jkza/stCapacityRank/' + id,
    method: 'delete'
  })
}
