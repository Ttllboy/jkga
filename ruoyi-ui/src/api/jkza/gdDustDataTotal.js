import request from '@/utils/request'

// 查询扬尘数据总列表
export function listGdDustDataTotal(query) {
  return request({
    url: '/jkza/gdDustDataTotal/list',
    method: 'get',
    params: query
  })
}

// 查询扬尘数据总详细
export function getGdDustDataTotal(id) {
  return request({
    url: '/jkza/gdDustDataTotal/' + id,
    method: 'get'
  })
}

// 新增扬尘数据总
export function addGdDustDataTotal(data) {
  return request({
    url: '/jkza/gdDustDataTotal',
    method: 'post',
    data: data
  })
}

// 修改扬尘数据总
export function updateGdDustDataTotal(data) {
  return request({
    url: '/jkza/gdDustDataTotal',
    method: 'put',
    data: data
  })
}

// 删除扬尘数据总
export function delGdDustDataTotal(id) {
  return request({
    url: '/jkza/gdDustDataTotal/' + id,
    method: 'delete'
  })
}
