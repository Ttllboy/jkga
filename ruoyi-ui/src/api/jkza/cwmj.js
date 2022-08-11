import request from '@/utils/request'

// 查询测温门禁列表
export function listCwmj(query) {
  return request({
    url: '/jkza/cwmj/list',
    method: 'get',
    params: query
  })
}

// 查询测温门禁详细
export function getCwmj(id) {
  return request({
    url: '/jkza/cwmj/' + id,
    method: 'get'
  })
}

// 新增测温门禁
export function addCwmj(data) {
  return request({
    url: '/jkza/cwmj',
    method: 'post',
    data: data
  })
}

// 修改测温门禁
export function updateCwmj(data) {
  return request({
    url: '/jkza/cwmj',
    method: 'put',
    data: data
  })
}

// 删除测温门禁
export function delCwmj(id) {
  return request({
    url: '/jkza/cwmj/' + id,
    method: 'delete'
  })
}
